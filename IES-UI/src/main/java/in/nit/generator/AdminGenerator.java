package in.nit.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AdminGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "IES_ACC_";

		try { 
			Connection con = session.connection();
			Statement stmt=con.createStatement(); 
			String sql =
					"select count(acc_Id)as Id from final.ACCOUNT_DTLS"; 
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{ 
				Long id=rs.getLong(1)+33587336222L;
				String generatedId = prefix + new Long(id).toString();
				System.out.println("Generated Id: " + generatedId); return generatedId; }
		}catch (Exception e) { 
			e.printStackTrace(); 
			} 
		return null;


		/*
		 * int random=new Random().nextInt(10); String dte=new
		 * SimpleDateFormat("ddMMyyyy").format(new Date()); return
		 * prefix+dte+"-"+random;
		 */

	}

}
