import java.util.ArrayList;
import java.util.List;

public class Agendamento {
    private List<Cliente> clientes;

    public Agendamento() {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
