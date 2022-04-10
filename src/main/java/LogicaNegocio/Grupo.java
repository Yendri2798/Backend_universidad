package LogicaNegocio;

public class Grupo {
    private int numero_Grupo;
    private String horario;
    private Ciclo ciclo;
    private Curso curso;
    private Profesor profesor;
    private int campos_Restantes;
    private int capacidad_Maxima;

    public Grupo(int numero_Grupo, String horario, Ciclo ciclo, Curso curso, Profesor profesor, int campos_Restantes, int capacidad_Maxima) {
        this.numero_Grupo = numero_Grupo;
        this.horario = horario;
        this.ciclo = ciclo;
        this.curso = curso;
        this.profesor = profesor;
        this.campos_Restantes = campos_Restantes;
        this.capacidad_Maxima = capacidad_Maxima;
    }

    public Grupo() {
        this.numero_Grupo = 0;
        this.horario = "";
        this.ciclo = new Ciclo();
        this.curso = new Curso();
        this.profesor = new Profesor();
        this.campos_Restantes = 0;
        this.capacidad_Maxima = 0;

    }

    public int getNumero_Grupo() {
        return numero_Grupo;
    }

    public void setNumero_Grupo(int numero_Grupo) {
        this.numero_Grupo = numero_Grupo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCampos_Restantes() {
        return campos_Restantes;
    }

    public void setCampos_Restantes(int campos_Restantes) {
        this.campos_Restantes = campos_Restantes;
    }

    public int getCapacidad_Maxima() {
        return capacidad_Maxima;
    }

    public void setCapacidad_Maxima(int capacidad_Maxima) {
        this.capacidad_Maxima = capacidad_Maxima;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "num_Grupo=" + numero_Grupo +
                ", horario='" + horario + '\'' +
                ", camposRestantes=" + campos_Restantes +
                ", capacidadMaxima=" + capacidad_Maxima +
                ", ciclo=" + ciclo +
                ", curso=" + curso +
                ", profesor=" + profesor +
                '}';
    }
}

