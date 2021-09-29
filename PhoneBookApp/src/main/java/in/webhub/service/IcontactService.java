package in.webhub.service;

import java.util.List;

import org.springframework.data.domain.Page;

import in.webhub.model.Contact;

public interface IcontactService {

	public boolean saveContact(Contact contact);
	
	public List<Contact> getAllContact();
	
	public Page<Contact> getAllContactNew(Integer pageNo,Integer pageSize);
	
	public Contact getContactById(Integer contactId);
	
	public boolean deleteById(Integer contactId);
	
	public Boolean isContactExists(Contact contact);
	
}
