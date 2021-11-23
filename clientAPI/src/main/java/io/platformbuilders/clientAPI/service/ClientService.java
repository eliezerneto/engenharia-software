package io.platformbuilders.clientAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.platformbuilders.clientAPI.entity.Client;
import io.platformbuilders.clientAPI.exception.NegocioException;
import io.platformbuilders.clientAPI.exception.RegistroNaoEncontradoException;
import io.platformbuilders.clientAPI.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository _clientRepository;

	public Page<Client> findAll(Specification<Client> clientSpec, Pageable pageable) {

		Page<Client> pageClient = _clientRepository.findAll(clientSpec, pageable);

		if (pageClient != null && !pageClient.isEmpty()) {
			return pageClient;
		}

		throw new RegistroNaoEncontradoException();
	}

	public Client findClientById(Long id) {

		Optional<Client> client = _clientRepository.findById(id);

		if (!client.isPresent())
			throw new RegistroNaoEncontradoException();

		return client.get();
	}

	public Client createClient(Client client) {

		try {

			return _clientRepository.save(client);

		} catch (Exception e) {
			throw new NegocioException("Problema ao tentar salvar Cliente!");
		}
	}

	public Client updateClient(Long id, Client newClient) {

		Optional<Client> oldClient = _clientRepository.findById(id);

		if (!oldClient.isPresent())
			throw new RegistroNaoEncontradoException();

		try {
			Client client = oldClient.get();

			if (newClient != null) {
				if (newClient.getName() != null) {
					client.setName(newClient.getName());
				}
				if (newClient.getPhone() != null) {
					client.setPhone(newClient.getPhone());
				}
				if (newClient.getEmail() != null) {
					client.setEmail(newClient.getEmail());
				}
				if (newClient.getBirthDate() != null) {
					client.setBirthDate(newClient.getBirthDate());
				}
			}

			return _clientRepository.save(client);

		} catch (Exception e) {
			throw new NegocioException("Problema ao tentar atualizar Cliente!");
		}
	}

	public void deleteClient(Long id) {

		Optional<Client> client = _clientRepository.findById(id);

		if (!client.isPresent())
			throw new RegistroNaoEncontradoException();

		try {
			_clientRepository.delete(client.get());

		} catch (Exception e) {
			throw new NegocioException("Problema ao tentar deletar Cliente!");
		}
	}
}