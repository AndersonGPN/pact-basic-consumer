package br.ce.wcaquino.consumer.tasks.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ce.wcaquino.consumer.tasks.model.Task;
import br.ce.wcaquino.consumer.utils.RequestHelper;

//3 passos do teste:
//	1 Configurar o ambiente - Arrange
//	2 Execução da tarefa    - Act
//	3 Validação da ação     - Assert


@RunWith(MockitoJUnitRunner.class)
public class TasksConsumerTest {
	
	private static final String INVALID_URL = "http://invalidURL.com";
	
	@InjectMocks
	private TasksConsumer consumer = new TasksConsumer(INVALID_URL);
	
	@Mock
	private RequestHelper helper;
	
	@Test
	public void shouldGetAnExistingTask() throws ClientProtocolException, IOException {
		// Arranje
		Map<String, String> expectedTask = new HashMap<String, String>();
		expectedTask.put("id", "1");
		expectedTask.put("task", "Task mocked!");
		expectedTask.put("dueDate", "2000-01-01");
		
		Mockito.when(helper.get(INVALID_URL + "/todo/1")).thenReturn(expectedTask);
		
		//Act
		Task task = consumer.getTask(1L);
		
		// Assert
		Assert.assertNotNull(task);
		Assert.assertThat(task.getId(), CoreMatchers.is(1L));
		
	}

}
