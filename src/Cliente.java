public class Cliente {
    private String nome;
    private int idade;
    private String cpf;
    private String numero;
    private String dataAgendamento;
    private String horario;
    private String sexualidade;

    public Cliente(String nome, int idade, String cpf, String numero, String dataAgendamento, String horario, String sexualidade) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.numero = numero;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
        this.sexualidade = sexualidade;
    }

    // Getters para cada campo
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumero() {
        return numero;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public String getHorario() {
        return horario;
    }

    public String getSexualidade() {
        return sexualidade;
    }

    // Implemente os setters para cada campo, se necessário
}
