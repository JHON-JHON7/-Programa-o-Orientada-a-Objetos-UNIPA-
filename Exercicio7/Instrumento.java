import java.util.ArrayList;

class Instrumento {
    private String nome;
    private String tipo;
    private ArrayList<Acessorio> acessorios = new ArrayList<>();

    public Instrumento(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public void adicionarAcessorio(String nome, String descricao) {
        acessorios.add(new Acessorio(nome, descricao));
    }

    public void removerAcessorio(Acessorio acessorio) {
        acessorios.remove(acessorio);
    }

    public ArrayList<Acessorio> getAcessorios() {
        return acessorios;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }


    class Acessorio {
        private String nome;
        private String descricao;

        public Acessorio(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}