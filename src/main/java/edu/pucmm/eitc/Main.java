package edu.pucmm.eitc;

import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Estudiante de prueba:
        int matricula = 20171056;
        String nombre = "Nelson";
        String carrera = "ISC";

        System.out.println("\nLista de estudiantes:\n");

        HttpResponse<List<Estudiante>> listaEstudiante = Unirest.get("http://localhost:7000/api/estudiante/")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .asObject(new GenericType<List<Estudiante>>() {});
        System.out.println("Respuesta: "+ listaEstudiante.getStatus());
        System.out.println("Mensaje: "+ listaEstudiante.getBody().toString());

        System.out.println("\nCreacion del estudiante:\n");

        HttpResponse<JsonNode> crearEstudiante = Unirest.post("http://localhost:7000/api/estudiante/")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .body(new Estudiante(matricula, nombre, carrera))
                .asJson();
        System.out.println("Respuesta: " + crearEstudiante.getStatus());
        System.out.println("Mensaje: " + crearEstudiante.getBody().toString());

        System.out.println("\nConsulta del estudiante:\n");

        Estudiante consultarEstudiante = Unirest.get("http://localhost:7000/api/estudiante/" + matricula)
                .asObject(Estudiante.class)
                .getBody();
        System.out.println("Matricula = " + consultarEstudiante.getMatricula() + ", Nombre = " + consultarEstudiante.getNombre() + ", Carrera = " + consultarEstudiante.getCarrera());

        System.out.println("\nEliminacion del estudiante:\n");

        HttpResponse deleteEstudiante = Unirest.delete("http://localhost:7000/api/estudiante/" + matricula).asEmpty();
        System.out.println("Respuesta: " + deleteEstudiante.getStatus());
    }

    static class Estudiante {
        private int matricula;
        private String nombre;
        private String carrera;

        public Estudiante(int matricula, String nombre, String carrera) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.carrera = carrera;
        }

        public int getMatricula() {
            return matricula;
        }

        public void setMatricula(int matricula) {
            this.matricula = matricula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCarrera() {
            return carrera;
        }

        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        @Override
        public String toString() {
            return "Estudiante{" +
                    "matricula=" + matricula +
                    ", nombre='" + nombre + '\'' +
                    ", carrera='" + carrera + '\'' +
                    '}';
        }
    }
}
