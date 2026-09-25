package dev.kaue.scheduling_system.controller;

import dev.kaue.scheduling_system.dto.ClienteRequestDTO;
import dev.kaue.scheduling_system.dto.ClienteResponseDTO;
import dev.kaue.scheduling_system.dto.EmpresaResponseDTO;
import dev.kaue.scheduling_system.model.Cliente;
import dev.kaue.scheduling_system.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ClienteResponseDTO criarCliente(@RequestBody ClienteRequestDTO requestDTO){

        Cliente cliente = new Cliente();
        cliente.setNomeCliente(requestDTO.nomeCLiente());

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(
                clienteSalvo.getId(),
                clienteSalvo.getNomeCliente()
        );
    }

    @GetMapping
    public List<ClienteResponseDTO> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteResponseDTO> listaClientes = new ArrayList<>();

        for (Cliente clienteAtual : clientes){
            ClienteResponseDTO dto = new ClienteResponseDTO(
                    clienteAtual.getId(),
                    clienteAtual.getNomeCliente()
            );
            listaClientes.add(dto);
        }
        return listaClientes;
    }
}
