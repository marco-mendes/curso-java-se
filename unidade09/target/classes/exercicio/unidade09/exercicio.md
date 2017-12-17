Codigo base para uso: amostra.unidade09

* Implemente um método para listar as contas de uma agencia que estao usando 
* cheque especial.

* Dica - Fragmento de código

 	public Map<String, Conta> coletaContasComUsoLimiteCredito() {
		return contas.entrySet().stream().
			   filter(PREDICADO).
			   collect(Collectors.toMap(
					     Map.Entry<String, Conta>::getKey, 
					     Map.Entry<String, Conta>::getValue)); 	
	}

 