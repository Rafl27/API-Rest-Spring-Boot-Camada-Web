package br.com.alura.forum.exception

//A classe herda de runtimeException
class NotFoundException(message : String?) : RuntimeException(message) {

}