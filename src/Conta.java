import exceptions.*;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if (valor <= 0) {
			throw new InvalidValueException("O valor do saque deve ser maior do que zero.");
		}
		if (valor > saldo) {
			throw new InvalidOperationException("Saldo insuficiente.");
		}
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			throw new InvalidValueException("O valor do depósito deve ser maior do que zero.");
		}
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if (contaDestino == this) {
			throw new InvalidOperationException("Não é possível transferir para si mesmo");
		}
		if (valor <= 0) {
			throw new InvalidValueException("O valor da transferência deve ser maior que zero.");
		}
		if (valor > saldo) {
			throw new InvalidOperationException("Saldo insuficiente.");
		}
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
