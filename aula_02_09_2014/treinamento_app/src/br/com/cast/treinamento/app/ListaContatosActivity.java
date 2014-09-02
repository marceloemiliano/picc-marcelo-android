package br.com.cast.treinamento.app;

import java.util.List;

import br.com.cast.treinamento.app.entity.Contato;
import br.com.cast.treinamento.app.service.ContatoService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListaContatosActivity extends ActionBarActivity {

	private ListView listViewContatos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_lista_contatos);
		
		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);

		listViewContatos.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				String mensagem = getString(R.string.voc_clicou_na_posicao, posicao);
				Toast.makeText(ListaContatosActivity.this, mensagem, Toast.LENGTH_LONG).show();
			}			
		});
		
		listViewContatos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				String mensagem = getString(R.string.voce_clicou_no_nome, adapter.getItemAtPosition(posicao));
				Toast.makeText(view.getContext(), mensagem, Toast.LENGTH_LONG).show();
				return true; //unico evento a ser executado da lista
			}
			
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		List<Contato> contatos = ContatoService.getInstancia().listarTodos();
		ArrayAdapter<Contato> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
		
		listViewContatos.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lista_contatos, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_novo:
			Intent intent = new Intent(this, ContatoActivity.class);
			super.startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
