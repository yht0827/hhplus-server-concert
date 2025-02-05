package kr.hhplus.be.server.support;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;

@Component
public class DataCleaner {

	private final EntityManager entityManager;

	public DataCleaner(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void cleanUp() {
		entityManager.getMetamodel().getEntities().forEach(entityType -> {
			Class<?> entityClazz = entityType.getJavaType();
			if (entityClazz.isAnnotationPresent(Table.class)) {
				Table tableAnnotation = entityClazz.getAnnotation(Table.class);
				String tableName = tableAnnotation.name();
				entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
			}
		});
	}
}
