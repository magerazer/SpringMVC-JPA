package fr.demos.formation.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import fr.demos.formation.model.Compte;

// on peut ne pas le mettre et le faire en xml seulement

public class CompteDAOMySQL implements CompteDAO {

	private Context context;
	
	// doit préparer le DataSource et c'est une ressource gérée par le serveur et non un bean 
	@Resource 
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	//@Transactional(rollbackFor=Exception.class)
	//@TransactionConfiguration(defaultRollback = true)
	public void insert(Compte c) throws Exception {
		
//		try (Connection cx = dataSource.getConnection()) {
//			PreparedStatement pstm = null;
//			System.out.println("avant insert");
//			
//			
//			pstm = cx.prepareStatement("INSERT INTO compte VALUES (?, ?, ?, ?)");
//			
//			pstm.setString(1, c.getMail());
//			pstm.setString(2, c.getNom());
//			pstm.setString(3, c.getPrenom());
//			pstm.setString(4, c.getAnneeNaissance().toString());
//			
//			pstm.executeUpdate();			
//			pstm.executeUpdate();
			
			
			
//		} catch(Exception ex) {
//			throw new RuntimeException();
//		}
		
		
		jdbcTemplate.update(
				"insert into Compte values (?,?,?,?) ",
				new Object[]{ c.getMail(), c.getNom(), c.getPrenom(), c.getAnneeNaissance().toString()}
				);
		jdbcTemplate.update(
				"insert into Compte values (?,?,?,?) ",
				new Object[]{ c.getMail(), c.getNom(), c.getPrenom(), c.getAnneeNaissance().toString()}
				);
		
	}
	
	@Override
	public List<Compte> selectAll() {
		// TODO Auto-generated method stub
		Compte c = null;

		List<Compte> comptes = new ArrayList();
		
		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			pstm = cx.prepareStatement("SELECT * " 
			+ "FROM Compte" 
			);
			
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String mail = rs.getString("mail");				
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");				
				LocalDate anneeNaissance = LocalDate.parse(rs.getString("anneeNaissance"));

				c = new Compte(mail, nom, prenom, anneeNaissance);
			
				comptes.add(c);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return comptes;
	}

	@Override
	public Compte select(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
