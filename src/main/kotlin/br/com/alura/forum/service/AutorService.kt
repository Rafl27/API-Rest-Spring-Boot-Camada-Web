package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutorService(var autores: List<Usuario>) {
    init{
        val autor = Usuario(
            id = 1,
            nome = "Autor",
            email = "autor@br.com.br",
        )
        autores = listOf(autor)
    }

    fun buscarPorId(id: Long): Usuario {
        return autores.first { it.id == id }
    }

}