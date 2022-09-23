package com.procesos.negocio.karen.controllers;

import aj.org.objectweb.asm.Opcodes;
import com.procesos.negocio.karen.models.Usuario;
import com.procesos.negocio.karen.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioControllers {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")

    /*public Usuario getUsuario(@PathVariable Long id){*/

    public Optional<Usuario> getUsuario(@PathVariable Long id){
        //seleccionar usuarios
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        /*
        agregar usuarios
        Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("fabian");
        usuario.setApellidos("Rincón Chinchilla");
        usuario.setDocumento("1064836389");
        usuario.setFechaNacimiento(new Date(2004,7,14));
        usuario.setDireccion("kdx-o10-310");
        usuario.setTelefono("3144454761");*/

        return usuario;
    }

    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario)
    {
        usuarioRepository.save(usuario);
        return usuario;
    }
    //Mostrar todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios()
    {
        return usuarioRepository.findAll();
    }
    //Traer por nombre

    @GetMapping("/usuario/{nombre}/{apellidos}")

    public List<Usuario> ListarPorNombreApellidos(@PathVariable String nombre, @PathVariable String apellidos)
    {
        return usuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);
    }

    @GetMapping("/usuarios/nombre/{nombre}")

    public List<Usuario> ListarPorNombre(@PathVariable String nombre)
    {
        return usuarioRepository.findAllByNombre(nombre);
    }

    @GetMapping("/usuarios/apellidos/{apellidos}")

    public List<Usuario> ListarPorApellidos(@PathVariable String apellidos)
    {
        return usuarioRepository.findAllByApellidos(apellidos);
    }
    //Modificar

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario)
    {
        Usuario usuarioBD = usuarioRepository.findById(id).get();
        try {
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioRepository.save(usuarioBD);

            return usuarioBD;
        }catch (Exception e){
            return null;
        }

    }

    @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id)
    {
        Usuario usuarioBD = usuarioRepository.findById(id).get();
        try {
            usuarioRepository.delete(usuarioBD);
            return usuarioBD;
        }catch (Exception e){
            return null;
        }

    }
}
