package afr.spelleritustest.service;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import org.mortbay.log.Log;

import afr.spelleritustest.dao.MySQLAccess;
import afr.spelleritustest.entity.Woordpakket;
import afr.spelleritustest.utils.Tools;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;

@Api(name = "woordpakketapi", version = "v3", description = "Een API om woordpakketten te beheren")
public class WoordpakkettenServiceAPI
{
	private static final  Logger LOGGER = Logger.getLogger(WoordpakkettenServiceAPI.class.getName());

	public WoordpakkettenServiceAPI()
	{
		LOGGER.setLevel(Level.INFO);
	}

	// TODO if the contentstring is empty only a 503 will be returned while it
	// has to be a 400
	@ApiMethod(name = "insertwoordpakket", path = "woordpakketapi/addwoordpakket")
	public Woordpakket addWoordpakket(@Named("identifier") String identifier,
			@Nullable @Named("description") String description, @Named("contents") String contents)
			throws BadRequestException
	{
		LOGGER.entering(getClass().getName(), "entering insertwoordpakket");
		// error checking
		// it is not mandatory to add a description so we will fill in a blank
		// space if we haven't got something
		if (description == null)
		{
			description = "";
		}
		if (identifier == null || identifier.trim() == "")
		{
			LOGGER.log(Level.INFO, "Geen naam gegeven aan het pakkket: " );
			throw new BadRequestException("Het pakket heeft een naam nodig, dit graag invullen");
		}
		if (description.length() > 200)
		{
			throw new BadRequestException("De beschrijving is te lang; maak hem iets korter");
		}
		if (contents.length() > 500)
		{
			throw new BadRequestException(
					"Pas het woordpakket aub aan, het is te lang. Het pakket mag maximaal 500 tekens bevatten!");
		}
		if (contents == null || contents.length() < 1)
		{
			throw new BadRequestException(
					"Het woordpakket is leeg, voeg woorden toe gescheiden door spaties of komma\'s!");
		}
		PreparedStatement query = null;
		Connection conn = null;
		identifier = Tools.makeWordList(identifier);
		contents = Tools.makeWordList(contents);
		description = Tools.makeWordList(description);
		try
		{
			conn = MySQLAccess.getConn();
			query = conn.prepareStatement("insert into spelleritus.woordpakketten (id,identifier,description,contents) values (default,?,?,?)");
			query.setString(1, identifier);
			query.setString(2, description);
			query.setString(3, contents);
			query.executeUpdate();
			conn.close();
			LOGGER.log(Level.INFO," Query gemaakt, connectie afgesloten");
		} catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.log(Level.INFO, "Query of verbinding met DB is fout gegaan....");

		} finally
		{
			if (conn != null)
				try
				{
					conn.close();
				} 
			catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		return new Woordpakket((long) 1, identifier, description, contents);
	}

	@ApiMethod(name = "getwpbyid", path = "woordpakketapi/getwpbyid")
	public Woordpakket getWpById(@Named("id") long id) throws NotFoundException, BadRequestException
	{
		if (id <= 0)
		{
			throw new BadRequestException("Vul een id in");
		}

		PreparedStatement query = null;
		Woordpakket wp = null;
		Connection conn = null;
		try
		{
			conn = MySQLAccess.getConn();
			query = conn.prepareStatement("select * from spelleritus.woordpakketten where id =?");
			query.setInt(1, (int) id);
			ResultSet rs = query.executeQuery();
			if (rs.next())
			{
				wp = new Woordpakket((long) rs.getInt("id"), rs.getString("identifier"), rs.getString("description"),
						rs.getString("contents"));
				query.close();
			} else
			{
				throw new NotFoundException("We hebben het bestand niet gevonden, probeer een andere zoekterm.");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			Log.debug("Error in connecting to db");
		} finally
		{
			if (conn != null)
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return wp;
	}

	@SuppressWarnings({ "unused" })
	@ApiMethod(name = "listwoordpakketten", path = "woordpakketapi/listwoordpakketten")
	public CollectionResponse<Woordpakket> listWoordpakketten(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit)
	{

		LOGGER.entering(getClass().getName(), "listWoordpakketten");
		LOGGER.log(Level.INFO, " Entering listwoordpakketten. cs: undefined limit undefined " );
		List<Woordpakket> wplijst = new ArrayList<Woordpakket>();
		PreparedStatement query = null;
		Woordpakket wp = null;
		Connection conn = null;
		try
		{
			conn = MySQLAccess.getConn();
			query = conn.prepareStatement("select * from spelleritus.woordpakketten ");
			ResultSet rs = query.executeQuery();
			while (rs.next())
			{
				wplijst.add(new Woordpakket(rs.getLong("id"), rs.getString("identifier"), rs.getString("description"),
						rs.getString("contents")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			if (conn != null)
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		LOGGER.exiting(getClass().getName(), "listWoordpakketten");
		return CollectionResponse.<Woordpakket> builder().setItems(wplijst).setNextPageToken(cursorString).build();
	}

	@SuppressWarnings({ "unused" })
	@ApiMethod(name = "searchwoordpakketen", path = "woordpakketapi/listwoordpakketten")
	public CollectionResponse<Woordpakket> searchWoordpakketten(@Named("searchterm") String searchterm)
	{
		List<Woordpakket> wplijst = new ArrayList<Woordpakket>();
		PreparedStatement query = null;
		Woordpakket wp = null;
		Connection conn = null;
		try
		{
			conn = MySQLAccess.getConn();
			query = conn.prepareStatement("select id,identifier,description,contents from spelleritus.woordpakketten ");
			ResultSet rs = query.executeQuery();
			while (rs.next())
			{
				String searcharena = rs.getString("identifier") + rs.getString("description")
						+ rs.getString("contents");
				if (searcharena.contains(searchterm))
				{
					wplijst.add(new Woordpakket(rs.getLong("id"), rs.getString("identifier"), rs
							.getString("description"), rs.getString("contents")));
				}
			}
			rs.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			if (conn != null)
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		return CollectionResponse.<Woordpakket> builder().setItems(wplijst).setNextPageToken("").build();

	}

	@ApiMethod(name = "updatewoordpakket")
	public Woordpakket updateWoordPakket(Woordpakket wp) throws BadRequestException, NotFoundException
	{
		if (wp.getId() <= 0)
		{
			throw new BadRequestException("Vul een id in");
		}

		PreparedStatement query = null;
		Connection conn = null;
		try
		{
			conn = MySQLAccess.getConn();
			query = conn.prepareStatement("select * from spelleritus.woordpakketten where id =?");
			query.setInt(1, wp.getId().intValue());
			ResultSet rs = query.executeQuery();
			if (rs.next())
			{
				query.clearBatch();
				query = conn.prepareStatement("update description,contents from spelleritus.woordpakketten where id=?");
				query.setInt(1, wp.getId().intValue());
				int i = query.executeUpdate();
				if (i != 1)
				{
					throw new NotFoundException("Update is niet gelukt...");
				}
			} else
			{
				throw new NotFoundException("We hebben het bestand niet gevonden, probeer een andere zoekterm.");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			Log.debug("Error in connecting to db");
		} finally
		{
			if (conn != null)
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return wp;

	}

}
