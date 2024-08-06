package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
			this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		// INICIAR VARIAVEIS DE COMUNICAÇAO COM A BD
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// STATEMENT QUE REQUESITA A BD
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM coursejdbc.seller INNER JOIN coursejdbc.department on seller.DepartmentId = department.Id WHERE seller.ID = ?");
			// PASSADO O PARAMETRO ESCOLHIDO "id"
			st.setInt(1, id);
			rs = st.executeQuery();
			//  SE A QUERY NÃO RETORNAR NUNHUM VALOR, NÃO EXISTE SELLER
			if (rs.next()) {
				
				Department dep = instanciateDepartment(rs);
				Seller obj = instanciateSeller(rs,dep);
				return obj;

			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	// METODO AUXILIAR 1
	private Department instanciateDepartment(ResultSet rs) throws SQLException  {
		 	Department dep = new Department();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setName(rs.getString("DepName"));
			return dep;
	}
	
	// METODO AUXILIAR 2
		private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
			Seller obj = new Seller();
			obj.setId(rs.getInt("Id"));
			obj.setName(rs.getString("Name"));
			obj.setEmail(rs.getString("Email"));
			obj.setBaseSalary(rs.getDouble("BaseSalary"));
			obj.setBirthdate(rs.getDate("BirthDate"));
			obj.setDepartment(dep);
			return obj;
		}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
