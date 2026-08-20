package com.cibertec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.entidades.Boleta;
import com.cibertec.entidades.BoletaHasProducto;
import com.cibertec.repository.BoletaHasProductoRepository;
import com.cibertec.repository.BoletaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoletaServiceImpl implements BoletaService{

	private final BoletaRepository boletaRepository;
	private final BoletaHasProductoRepository detalleRepository;
	
	@Override
	@Transactional
	public Boleta insertaBoleta(Boleta obj) {
		Boleta cabecera = boletaRepository.save(obj);
		for (BoletaHasProducto d : cabecera.getDetallesBoleta()) {
			d.getBoletaHasProductoPK().setIdBoleta(cabecera.getIdboleta());
			detalleRepository.actualizaStock(d.getCantidad(), d.getBoletaHasProductoPK().getIdProducto());
			detalleRepository.save(d);
		}
		return cabecera;
	}

	@Override
	public List<Boleta> listaBoleta() {
		return boletaRepository.findAll();
	}

}
