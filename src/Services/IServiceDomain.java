/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Domain;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public interface IServiceDomain {
  public void AddDomain(Domain d);
public List<Domain> AfficherDomain()throws SQLException;

}
