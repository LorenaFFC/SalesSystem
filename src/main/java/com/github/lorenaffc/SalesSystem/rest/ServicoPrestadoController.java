package com.github.lorenaffc.SalesSystem.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.lorenaffc.SalesSystem.model.entity.Cliente;
import com.github.lorenaffc.SalesSystem.model.entity.ServicoPrestado;
import com.github.lorenaffc.SalesSystem.model.repository.ClienteRepository;
import com.github.lorenaffc.SalesSystem.model.repository.ServicoPrestadoRepository;
import com.github.lorenaffc.SalesSystem.rest.dto.ServicoPrestadoDTO;
import com.github.lorenaffc.SalesSystem.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController  {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar( @RequestBody @Valid ServicoPrestadoDTO dto ){
   //     LocalDate data = LocalDate.parse(dto.getDataCadastro(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST, "Cliente inexistente."));


        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setDataCadastro(dto.getDataCadastro());
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setPreco( bigDecimalConverter.converter(dto.getPreco()));

        return repository.save(servicoPrestado);
    }


}