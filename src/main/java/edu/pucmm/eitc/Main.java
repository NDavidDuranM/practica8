package edu.pucmm.eitc;

import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola Unirest");

        HttpResponse<String> response = Unirest.get("http://localhost:7000/")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .asString();
        System.out.println("El codigo de respuesta es: " + response.getStatus());
        System.out.println("El mensaje: " + response.getBody());

        HttpResponse<List<Estudiante>> estudianteHttpResponse = Unirest.get("http://localhost:7000/api/estudiante/")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .asObject(new GenericType<List<Estudiante>>() {});
        System.out.println("El codigo de respuesta es: " + estudianteHttpResponse.getStatus());
        System.out.println("El mensaje: " + estudianteHttpResponse.getBody().toString());
    }

    class Estudiante {
        private int matricula;
        private String nombre;
        private String carrera;

        public Estudiante() {
        }

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
