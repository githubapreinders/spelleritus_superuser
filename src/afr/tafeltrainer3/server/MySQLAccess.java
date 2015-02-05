package afr.tafeltrainer3.server;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.Woordpakket;

import com.google.appengine.api.utils.SystemProperty;

public class MySQLAccess
{
	private Connection connect;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	public int flag = 0;

	// private tafeltrainer3messages messages;

	public MySQLAccess()
	{
	}

	private Connection getConn()
	{

		connect = null;
		String url = "";
		String db = "";
		String driver = "";
		String user = "";
		String pass = "";

		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
		{
			url = "jdbc:google:mysql://subtle-reserve-547:mysql-instance/";
			driver = "com.mysql.jdbc.GoogleDriver";
			db = "mysql";
			user = "root";
		} else
		{
			url = "jdbc:mysql://localhost/";
			driver = "com.mysql.jdbc.Driver";
			db = "spelleritus";
			user = "root";
			pass = "Osiris74";
		}

		try
		{
			Class.forName(driver).newInstance();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)

				connect = DriverManager.getConnection(url + db + "?user=" + user);
			else
			{
				connect = DriverManager.getConnection(url + db, user, pass);
			}
		} catch (SQLException e)
		{
			System.err.println("Mysql Connection Error: ");
			e.printStackTrace();
		}
		return connect;
	}

	public SuperUser findPw(String emailadress)
	{
		SuperUser su = null;
		try
		{
			connect = getConn();
			preparedStatement = connect.prepareStatement("select * from spelleritus.begeleider" + " where email = '"
					+ emailadress + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				Random ran = new Random();
				int i = 1000 + ran.nextInt(9999);
				String password = String.valueOf(i);
				su = new SuperUser(resultSet.getString("name"), emailadress, password);
				Encrypter e = Encrypter.getInstance();
				String passw = e.encrypt(password);
				preparedStatement = connect.prepareStatement("update spelleritus.begeleider set " + "password = '"
						+ passw + "' where email = '" + emailadress + "'");
				i = preparedStatement.executeUpdate();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return su;
	}

	public boolean verifyMailadress(String code)
	{
		boolean returnvalue = false;
		try
		{
			connect = getConn();
			preparedStatement = connect.prepareStatement("select * from spelleritus.begeleider"
					+ " where verificationcode = '" + code + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				preparedStatement = connect.prepareStatement("update spelleritus.begeleider "
						+ " set verified = 1 where verificationcode = '" + code + "'");
				int geslaagd = preparedStatement.executeUpdate();
				if (geslaagd == 1)
				{
					returnvalue = true;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		return returnvalue;
	}

	public SuperUser retrieveSuperUser(String email, String password)
	{
		connect = getConn();
		Encrypter encrypter = Encrypter.getInstance();
		String passw = encrypter.encrypt(password);
		SuperUser resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.begeleider where password =?"
					+ "and email =?");
			preparedStatement.setString(1, passw);
			preparedStatement.setString(2, email);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new SuperUser(resultSet.getString("name"), resultSet.getString("email"), password,
						resultSet.getInt("emailfrequency"), resultSet.getString("haspackages"),
						resultSet.getBoolean("verified"));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in getuser of superuser onbekend");
			return resultuser;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return resultuser;
	}

	public ArrayList<SuperUser> getSuperUserList()
	{
		connect = getConn();
		ArrayList<SuperUser> resultusers = new ArrayList<SuperUser>();
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.begeleider");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultusers.add(new SuperUser(resultSet.getString("name"), resultSet.getString("email"), resultSet
						.getString("password"), resultSet.getInt("emailfrequency")));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in getuser of superuser onbekend");
			return resultusers;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return resultusers;
	}

	public SuperUser addNewSuperUser(SuperUser superuser)
	{
		connect = getConn();
		ArrayList<Woordpakket> woordpakketten = new ArrayList<Woordpakket>();
		SuperUser resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.begeleider where email =?");
			preparedStatement.setString(1, superuser.getEmail());
			resultSet = preparedStatement.executeQuery();
			resultSet.first();
			if (resultSet.getRow() > 0)
			{
				if (resultSet.getRow() == 1)
				{
					resultuser = new SuperUser("Emailadres bestaat al", "Of nog niet geverifieerd");
					preparedStatement.close();
					resultSet.close();
					connect.close();
					return resultuser;
				}
				resultuser = new SuperUser("Emailadres bestaat al", "Of nog niet geverifieerd");
				return resultuser;
			}
			// TODO packages ophalen
			preparedStatement = connect.prepareStatement("select * from spelleritus.woordpakketten where ismutable=0");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				Woordpakket wp = new Woordpakket(resultSet.getString("identifier"), resultSet.getString("description"),
						resultSet.getString("contents"));
				woordpakketten.add(wp);
			}

			preparedStatement = connect.prepareStatement("select id from spelleritus.woordpakketten");
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			int lastitem = resultSet.getInt("id");
			ArrayList<Integer> wpids = new ArrayList<Integer>();

			for (Woordpakket w : woordpakketten)
			{
				wpids.add(lastitem);
				lastitem++;
				preparedStatement = connect
						.prepareStatement("insert into spelleritus.woordpakketten (id,identifier,description,contents) values (?,?,?,?)");
				preparedStatement.setInt(1, lastitem);
				preparedStatement.setString(2, w.getIdentifier());
				preparedStatement.setString(3, w.getDescription());
				preparedStatement.setString(4, w.getContents());
				preparedStatement.executeUpdate();
			}
			StringBuilder mywps = new StringBuilder("");
			for (Integer i : wpids)
			{
				mywps.append(String.valueOf(i) + " ");
			}

			preparedStatement = connect
					.prepareStatement("insert into spelleritus.begeleider "
							+ "(id,name,email,password,emailfrequency,verificationcode,haspackages,verified,sendadress) values (default,?,?,?,?,?,?,default,?)");
			preparedStatement.setString(1, superuser.getName());
			preparedStatement.setString(2, superuser.getEmail());
			preparedStatement.setString(3, superuser.getPassword());
			preparedStatement.setInt(4, superuser.getEmailfrequency());
			preparedStatement.setString(5, superuser.getVerificationcode());
			preparedStatement.setString(6, mywps.toString());
			preparedStatement.setString(7, superuser.getSendaddress());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();

		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in addnew superuser...");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return superuser;
	}

	public void updateUserMetaData(int id)
	{
		connect = getConn();

		try
		{
			int howmuchopgaven = 0;
			int errors = 0;
			preparedStatement = connect.prepareStatement("select howmuchopgaven,howmucherrors from spelleritus.sessie"
					+ " where userid =" + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				howmuchopgaven = howmuchopgaven + resultSet.getInt("howmuchopgaven");
				errors += resultSet.getInt("howmucherrors");
			}
			preparedStatement.clearParameters();
			double percentage = 0;
			if (howmuchopgaven > 0)
			{
				percentage = round(((double) (howmuchopgaven - errors) * 100) / (double) howmuchopgaven, 1);
			}
			double averagespeed = 0;

			preparedStatement = connect.prepareStatement("select howmuchopgaven,averagespeed from spelleritus.sessie"
					+ " where userid = " + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				averagespeed += (resultSet.getDouble("averagespeed") * ((double) resultSet.getInt("howmuchopgaven") / (double) howmuchopgaven));
			}
			averagespeed = round(averagespeed, 1);
			preparedStatement.clearParameters();

			preparedStatement = connect
					.prepareStatement("update spelleritus.summaryres set howmuchopgaven = ?,accuracy = ?,"
							+ " speed = ? where userid = " + id);
			preparedStatement.setInt(1, howmuchopgaven);
			preparedStatement.setDouble(2, percentage);
			preparedStatement.setDouble(3, averagespeed);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("updateusermetadata");

		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	public ArrayList<Woordpakket> retrieveWps(SuperUser superuser)
	{
		connect = getConn();
		ArrayList<Woordpakket> woordpakketten = new ArrayList<Woordpakket>();
		Woordpakket resultWoordpakket = null;
		woordpakketten.add(resultWoordpakket);
		try
		{
			ArrayList<Integer> wpnumbers = new ArrayList<Integer>();

			String[] wpnhelper = superuser.getPackages().split("[\\s+,\\s+]");
			for (String s : wpnhelper)
			{
				wpnumbers.add(Integer.parseInt(s));
			}
			for (Integer number : wpnumbers)
			{
				preparedStatement = connect.prepareStatement("select * from spelleritus.woordpakketten where id=?");
				preparedStatement.setInt(1, number);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
				{
					resultWoordpakket = new Woordpakket((long) resultSet.getInt("id"),
							resultSet.getString("identifier"), resultSet.getString("description"),
							resultSet.getString("contents"));
					woordpakketten.add(resultWoordpakket);
				}
			}
			woordpakketten.remove(0);
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in getgroup");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return woordpakketten;

	}

	public ArrayList<User> getGroup(SuperUser superuser)
	{
		connect = getConn();
		ArrayList<User> users = new ArrayList<User>();
		User resultuser = null;
		users.add(resultuser);
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.user where emailsuperuser=?");
			preparedStatement.setString(1, superuser.getEmail());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"),
						resultSet.getString("name"), resultSet.getString("familyname"),
						resultSet.getString("loginname"), resultSet.getString("password"),
						resultSet.getBoolean("houindegaten"), resultSet.getInt("currentpackage"),
						resultSet.getInt("extrapackage"));
				users.add(resultuser);
			}
			users.remove(0);
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in getgroup");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return users;
	}

	// deletes a user and the accompanying records
	public void deleteUser(User user)
	{
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement(" delete from spelleritus.user where id=?");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	// maakt een nieuwe user aan
	public User addNewUser(User user)
	{
		int lastrow = -1;
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.user" + " where loginname='"
					+ user.getLoginname() + "'and password='" + user.getPassword() + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.getRow() != 0)
			{
				preparedStatement.close();
				connect.close();
				return user;
			}
			resultSet.close();

			preparedStatement = connect.prepareStatement("insert into spelleritus.user"
					+ "(id,emailsuperuser,name,familyname,loginname,password,houindegaten,currentpackage,extrapackage)"
					+ "values (default,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getEmailsuperuser());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getFamilyname());
			preparedStatement.setString(4, user.getLoginname());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setBoolean(6, user.getHouindegaten());
			preparedStatement.setInt(7, user.getCurrentwp());
			preparedStatement.setInt(8, user.getExtrawp());
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("select * from spelleritus.user where id = "
					+ " all(select max(id)from spelleritus.user)");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			lastrow = resultSet.getInt("id");
			preparedStatement.close();
			resultSet.close();
			connect.close();

		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in addNewUser");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return new User(lastrow, user.getEmailsuperuser(), user.getName(), user.getFamilyname(), user.getLoginname(),
				user.getPassword(), user.getHouindegaten(), user.getCurrentwp(), user.getExtrawp());
	}

	// Updates an existing wp , or inserts a new one if the identifier is
	// changed by the superuser
	public boolean updateWoordpakket(Woordpakket wp, SuperUser su)
	{
		boolean result = false;
		connect = getConn();
		try
		{
			preparedStatement = connect
					.prepareStatement("select identifier from spelleritus.woordpakketten where identifier=?");
			preparedStatement.setString(1, wp.getIdentifier());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				preparedStatement = connect.prepareStatement("update spelleritus.woordpakketten "
						+ "set identifier=?, description=?, contents=?" + "where id=? ");
				preparedStatement.setString(1, wp.getIdentifier());
				preparedStatement.setString(2, wp.getDescription());
				preparedStatement.setString(3, wp.getContents());
				preparedStatement.setInt(4, wp.getId().intValue());
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} else
			{
				preparedStatement = connect
						.prepareStatement("insert into spelleritus.woordpakketten (id,identifier,description,contents) values (default,?,?,?,default)");
				preparedStatement.setString(1, wp.getIdentifier());
				preparedStatement.setString(2, wp.getDescription());
				preparedStatement.setString(3, wp.getContents());
				preparedStatement.executeUpdate();
				preparedStatement = connect.prepareStatement("select * from spelleritus.woordpakketten");
				resultSet = preparedStatement.executeQuery();
				resultSet.last();
				int newid = -1;
				newid = resultSet.getInt("id");
				if (newid > 0)
				{
					preparedStatement = connect.prepareStatement("select * from spelleritus.begeleider where email=?");
					preparedStatement.setString(1, su.getEmail());
					resultSet = preparedStatement.executeQuery();
					resultSet.first();
					StringBuilder str = new StringBuilder(resultSet.getString("haspackages"));
					str.append(" " + String.valueOf(newid));
					preparedStatement = connect
							.prepareStatement("update from spelleritus.begeleider set haspackages = ? where email=?");
					preparedStatement.setString(1, str.toString());
					preparedStatement.setString(2, su.getEmail());
					preparedStatement.executeUpdate();
				}

			}

		}

		catch (Exception e)
		{
			e.printStackTrace();
			return result = false;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return result;
	}

	// werkt gebruikersgegevens van een begeleider bij
	public void updateSuperUser(SuperUser superuser, String oldemail)
	{
		connect = getConn();
		Encrypter encrypt = Encrypter.getInstance();
		String passw = encrypt.encrypt(superuser.getPassword());
		try
		{
			/**
			 * checkt eerst of het nieuwe emailadres al niet in de lijst staat,
			 * daarna of het emailadres is veranderd ten opzichte van wat er al
			 * stond; zo ja, moeten de gegevens van zijn users ook bijgewerkt
			 * worden want dit emailadres verbindt steeds een user met zijn
			 * begeleider.
			 */
			if (superuser.getEmail() != oldemail)
			{
				preparedStatement = connect.prepareStatement("select email from spelleritus.begeleider ");
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
				{
					if (resultSet.getString("email").equals(superuser.getEmail()))
					{
						return;
					}
				}
				preparedStatement = connect.prepareStatement("update spelleritus.user set"
						+ " emailsuperuser = ? where emailsuperuser = '" + oldemail + "'");
				preparedStatement.setString(1, superuser.getEmail());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				preparedStatement = connect.prepareStatement("update spelleritus.summaryres"
						+ " set emailsuperuser = ? where emailsuperuser ='" + oldemail + "'");
				preparedStatement.setString(1, superuser.getEmail());
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			preparedStatement = connect
					.prepareStatement("update spelleritus.begeleider set "
							+ " name = ? , email = ?, password = ? , emailfrequency = ?  " + "where email = '"
							+ oldemail + "'");
			preparedStatement.setString(1, superuser.getName());
			preparedStatement.setString(2, superuser.getEmail());
			preparedStatement.setString(3, passw);
			preparedStatement.setInt(4, superuser.getEmailfrequency());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		}

		catch (Exception e)
		{
			System.out.println("fout in updatesuperuser");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	/**
	 * schrijft de veranderingen van een user bij in de database. bijv geld of
	 * gekochte goederen.
	 */
	public void updateUser(User user)
	{
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("update spelleritus.user set emailsuperuser=?,name=?,"
					+ "familyname=?  ,loginname=?  ,password=? ,houindegaten=? "
					+ ",currentpackage=? ,extrapackage=? where id =?");
			preparedStatement.setString(1, user.getEmailsuperuser());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getFamilyname());
			preparedStatement.setString(4, user.getLoginname());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setBoolean(6, user.getHouindegaten());
			preparedStatement.setInt(7, user.getCurrentwp());
			preparedStatement.setInt(8, user.getExtrawp());
			preparedStatement.setInt(9, user.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in updateuser");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	public void superuserUpdatesUser(User user)
	{
		connect = getConn();

		try
		{
			preparedStatement = connect.prepareStatement("update spelleritus.user set emailsuperuser=?,name=?,"
					+ "familyname=? ,groupname=? ,loginname=? ,money=?,houindegaten=? where id = " + user.getId());
			preparedStatement.setString(1, user.getEmailsuperuser());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getFamilyname());
			preparedStatement.setString(4, user.getGroupname());
			preparedStatement.setString(5, user.getLoginname());
			preparedStatement.setInt(6, user.getMoney());
			preparedStatement.setBoolean(7, user.getHouindegaten());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
			connect = getConn();
			preparedStatement = connect.prepareStatement("update spelleritus.summaryres set"
					+ " username = ?,familyname = ? where userid =" + user.getId());
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getFamilyname());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();

		} catch (Exception e)
		{
			System.out.println("fout in administratorUpdatesUser");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	public User getUser(String loginname, String password)
	{
		connect = getConn();
		User resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.user where" + " loginname='"
					+ loginname + "'and password='" + password + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"),
						resultSet.getString("name"), resultSet.getString("familyname"),
						resultSet.getString("loginname"), resultSet.getString("password"),
						resultSet.getBoolean("houindegaten"));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in getuser of user onbekend");
			return resultuser;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		return resultuser;
	}

	public User getUserById(int id)
	{
		connect = getConn();
		User resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.user where" + " id =" + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"),
						resultSet.getString("name"), resultSet.getString("familyname"),
						resultSet.getString("loginname"), resultSet.getString("password"),
						resultSet.getBoolean("houindegaten"));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in getuserById of user onbekend");
			return resultuser;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return resultuser;
	}

	public void closeAll()
	{
		try
		{
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static double round(double value, int places)
	{
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	public Woordpakket getCurrentWp(int userid)
	{
		connect = getConn();
		Woordpakket wp = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from spelleritus.user where id=?");
			preparedStatement.setInt(1, userid);
			resultSet = preparedStatement.executeQuery();
			int currentpackage = 0 ;
			while(resultSet.next())
			{
				currentpackage = resultSet.getInt("currentpackage");
			}
			if(currentpackage > 0)
			{
				preparedStatement = connect.prepareStatement("select * from spelleritus.woordpakketten where id = ?");
				preparedStatement.setInt(1, currentpackage);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					wp = new Woordpakket((long)resultSet.getInt("id"), resultSet.getString("identifier"), resultSet.getString("description"),resultSet.getString("contents"));
				}
			}
			resultSet.close();
			connect.close();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return wp;
	}

}