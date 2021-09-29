package in.nit.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import in.nit.model.CoTriggersEntity;

@Repository("coTrgDao")
public class CoTriggersDaoImpl implements CoTriggersDao {

	@Override
	public List<CoTriggersEntity> findPendTriggersWithOraHash(String status, Integer tb, Integer ci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CoTriggersEntity> findPendTriggers(String status) {
		List<CoTriggersEntity> entities = new ArrayList<>();
		
		String sql ="SELECT* FROM CO_TRIGGERS cot WHERE " +"cot.TRG_STATUS=? ";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "root");
			PreparedStatement sts = con.prepareStatement(sql);
			sts.setString(1, status);
			
			ResultSet rs = sts.executeQuery();
			
			while (rs.next()) {
				CoTriggersEntity entity = new CoTriggersEntity();
				entity.setCaseNum(rs.getLong("CASE_NUM"));
				entity.setTriggerId(rs.getInt("TRG_ID"));
				entity.setTriggerStatus(rs.getString("TRG_STATUS"));
				entities.add(entity);
			}
			sts.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entities;
	}

}
