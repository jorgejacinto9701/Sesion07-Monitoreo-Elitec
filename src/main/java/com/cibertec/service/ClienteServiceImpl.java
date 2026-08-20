package com.cibertec.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.entidades.Cliente;
import com.cibertec.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	private final  ClienteRepository repository;

	@Override
	public List<Cliente> listaCliente(String filtro, Pageable ageable) {
		return repository.listaCliente(filtro, ageable);
	}

}
