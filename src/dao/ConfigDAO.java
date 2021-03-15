package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import entity.Config;
import util.DBUtil;

public class ConfigDAO {
	public int getTotal() {
		int total = 0;
		try (Connection con = DBUtil.getConnection();
				Statement st = con.createStatement()) {
			String sql = "SELECT COUNT(*) FROM CONFIG";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("Total:" + total);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(Config config) {
		String sql = "INSERT INTO config VALUES(null, ?, ?)";
		try(Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, config.getKey());
			ps.setString(2, config.getValue());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				config.setId(rs.getInt("id"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Config config) {
		String sql = "UPDATE config SET key_ = ?, value = ? where id = ?";
		try(Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, config.getKey());
			ps.setString(2, config.getValue());
			ps.setInt(3, config.getId());
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Config config) {
		String sql = "DELETE FROM config WHERE id = ?";
		try(Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, config.getId());
			int effected = ps.executeUpdate();
			System.out.println("Effected count: " + effected);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Config get(Config config) {
		String sql = "SELECT * FROM config WHERE id = ?";
		try(Connection con = DBUtil.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, config.getId());
			ResultSet rs = ps.executeQuery();
			config = null;
			if(rs.next()) {
				config = new Config();
				config.setId(config.getId());
				config.setKey(rs.getString("key_"));
				config.setValue(rs.getString("value"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public Config getByKey(String key) {
		String sql = "SELECT * FROM config Where key_ = ?";
		Config config = null;
		try(Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				config = new Config();
				config.setId(rs.getInt("id"));
				config.setKey(rs.getString("key_"));
				config.setValue(rs.getString("value"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public List<Config> list(int start, int count) {
		String sql = "SELECT * FROM config ORDER BY id DESC LIMIT ?, ?";
		List<Config> res = new ArrayList<>();
		try(Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Config config = new Config();
				config.setId(rs.getInt("id"));
				config.setKey(rs.getString("key_"));
				config.setValue(rs.getString("value"));
				res.add(config);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Config> list() {
		return list(0, Short.MAX_VALUE);
	}
}
