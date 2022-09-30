package com.procesos.negocio.karen.controllers;

import aj.org.objectweb.asm.Opcodes;
import com.procesos.negocio.karen.models.Usuario;
import com.procesos.negocio.karen.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

   /* public Optional<Usuario> getUsuario(@PathVariable Long id){*/
    public ResponseEntity getUsuario(@PathVariable Long id){
        //seleccionar usuarios
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent())
        {
            return new ResponseEntity(usuario, HttpStatus.OK);
        }


        //agregar usuarios
        /*Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("fabian");
        usuario.setApellidos("Rincón Chinchilla");
        usuario.setDocumento("1064836389");
        usuario.setFechaNacimiento(new Date(2004,7,14));
        usuario.setDireccion("kdx-o10-310");
        usuario.setTelefono("3144454761");*/

        //return usuario;
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario")
    /*public Usuario crearUsuario(@RequestBody Usuario usuario)*/
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario)
    {
        try {
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario,HttpStatus.CREATED);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
        //return usuario;
    }
    //Mostrar todos los usuarios

    @GetMapping("/usuarios")
    //List<Usuario>
    public  ResponseEntity listarUsuarios()
    {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
       // return usuarioRepository.findAll();
    }


    //Traer por nombre

    @GetMapping("/usuario/{nombre}/{apellidos}")

    public ResponseEntity ListarPorNombreApellidos(@PathVariable String nombre, @PathVariable String apellidos)
    {
        List<Usuario> usuarios = usuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);
        if (usuarios.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuarios/nombre/{nombre}")

    public ResponseEntity ListarPorNombre(@PathVariable String nombre)
    {
        List<Usuario> usuarios = usuarioRepository.findAllByNombre(nombre);
        if (usuarios.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuarios/apellidos/{apellidos}")

    public ResponseEntity ListarPorApellidos(@PathVariable String apellidos)
    {
        List<Usuario> usuarios = usuarioRepository.findAllByApellidos(apellidos);
        if (usuarios.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    //Modificar

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario)
    {
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);
        if(usuarioBD.isPresent())
        {
            try {
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellidos(usuario.getApellidos());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                usuarioRepository.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD,HttpStatus.OK);
            }catch (Exception e)
            {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id)
    {
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);
        if(usuarioBD.isPresent())
        {
            usuarioRepository.delete(usuarioBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();



    }
}
/*karen*/