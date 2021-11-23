package io.platformbuilders.clientAPI.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.platformbuilders.clientAPI.entity.Client;
import io.platformbuilders.clientAPI.service.ClientService;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/api/v1/clients", method = RequestMethod.GET)
	public Page<Client> findClient(@And({ @Spec(path = "name", spec = Like.class),
			@Spec(path = "email", spec = Like.class), @Spec(path = "phone", spec = Like.class),
			@Spec(path = "birthDate", params = { "dateAfter",
					"dateBefore" }, spec = Between.class, config = "dd-MM-yyyy") }) Specification<Client> clientSpec,
			Pageable pageable) {

		return clientService.findAll(clientSpec, pageable);

	}

	@RequestMapping(value = "/api/v1/clients/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> findClientById(@PathVariable(value = "id") Long id) {

		Client client = clientService.findClientById(id);
		return new ResponseEntity<Client>(client, HttpStatus.OK);

	}

	@RequestMapping(value = "/api/v1/clients", method = RequestMethod.POST)
	public Client createClient(@Valid @RequestBody Client client) {

		return clientService.createClient(client);
	}

	@RequestMapping(value = "/api/v1/clients/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Client newClient) {

		Client client = clientService.updateClient(id, newClient);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/v1/clients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id) {

		clientService.deleteClient(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
