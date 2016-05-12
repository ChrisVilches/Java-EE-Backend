package facade;

import java.util.List;

import javax.ejb.Local;

import model.Ban;

@Local
public interface BanFacade {
	

	public void create(Ban entity);

	public void edit(Ban entity);

	public void remove(Ban entity);

	public Ban find(Object id);

	public List<Ban> findAll();

	public List<Ban> findRange(int[] range);

	public int count();

}