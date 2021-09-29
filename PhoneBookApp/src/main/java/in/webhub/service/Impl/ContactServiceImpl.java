package in.webhub.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.webhub.model.Contact;
import in.webhub.repository.ContactRepository;
import in.webhub.service.IcontactService;

@Service
public class ContactServiceImpl implements IcontactService {

	@Autowired
	private ContactRepository repo;
	
	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("Y");
		Contact saveEntity = repo.save(contact);
		if (saveEntity !=null && saveEntity.getContactId()!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> getAllContact() {
		Contact contactFilter = new Contact();
		contactFilter.setActiveSw("Y"); 
		Example<Contact> example = Example.of(contactFilter);
		List<Contact> contacts = repo.findAll(example);
		return contacts;
	}
	
	@Override
	public Page<Contact> getAllContactNew(Integer pageNo,Integer pageSize) {
		Contact contactFilter = new Contact();
		contactFilter.setActiveSw("Y"); 
		Example<Contact> example = Example.of(contactFilter);
		
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		
		Page<Contact> findAll= repo.findAll(example,pageRequest);
		
		return findAll;
	}
	

	@Override
	public boolean deleteById(Integer contactId) {
		/*boolean existById = repo.existsById(contactId);
		if (existById) {
			repo.deleteById(contactId);
			return true;
		}
		return false;*/
		
		Optional<Contact> findById= repo.findById(contactId);
		if (findById.isPresent()) {
			Contact contact = findById.get();
			contact.setActiveSw("N");
			repo.save(contact);	
			return true;
		}
		return false;
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> opt =repo.findById(contactId);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Boolean isContactExists(Contact contact) {
		Example<Contact> example = Example.of(contact);
		return repo.exists(example);
	}
}
