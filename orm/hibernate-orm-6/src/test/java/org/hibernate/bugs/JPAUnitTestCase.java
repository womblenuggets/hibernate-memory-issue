package org.hibernate.bugs;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.querydsl.*;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import jakarta.persistence.*;
import org.hibernate.bugs.entity.*;
import org.junit.jupiter.api.*;

import java.util.UUID;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@BeforeEach
	void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@AfterEach
	void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	void hhh123Test() throws Exception {
		// Give time to connect VisualVM, GC, start memory sampling
		Thread.sleep(30000);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// DO STUFF
		UUID applicationId = UUID.randomUUID();

		CriteriaBuilderConfiguration config = Criteria.getDefault();
		var criteriaBuilder = config.createCriteriaBuilderFactory(entityManagerFactory);

		QDatabaseStatus qDatabaseStatus = QDatabaseStatus.databaseStatus;
		QFactorDatabase qFactorDatabase = QFactorDatabase.factorDatabase;
		QPartition qPartition = QPartition.partition;
		QDatabaseStatistics qDatabaseStatistics = QDatabaseStatistics.databaseStatistics;

		BlazeJPAQuery<QFactorDatabase> query = new BlazeJPAQuery<>(entityManager, criteriaBuilder);
		query
				.select(QFactorDatabase.factorDatabase)
				.from(latestDatabaseStatusSubQuery(applicationId), qDatabaseStatus)
				.innerJoin(qFactorDatabase)
				.on(qDatabaseStatus.id.applicationId.eq(qFactorDatabase.applicationId))
				.innerJoin(qPartition)
				.on(qFactorDatabase.partitionId.eq(qPartition.partitionId))
				.leftJoin(latestDatabaseStatisticsSubQuery(), qDatabaseStatistics)
				.on(qDatabaseStatistics.id.applicationId.eq(qDatabaseStatus.id.applicationId));

		var result = query.fetchFirst();

		entityManager.getTransaction().commit();
		entityManager.close();

		// Give time to see heap usage in Visual VM
		Thread.sleep(30000);
	}

	public static BlazeJPAQuery<DatabaseStatistics> latestDatabaseStatisticsSubQuery() {
		QDatabaseStatistics qLatestDatabaseStatistics = new QDatabaseStatistics("latestDatabaseStatistics");
		QDatabaseStatistics qTempLatestDatabaseStatistics = new QDatabaseStatistics("tempLatestDatabaseStatistics");
		QDatabaseStatisticsCte qDatabaseStatisticsCte = QDatabaseStatisticsCte.databaseStatisticsCte;

		return JPQLNextExpressions
				.select(qLatestDatabaseStatistics)
				.from(qLatestDatabaseStatistics)
				.innerJoin(JPQLNextExpressions
								   .select(qDatabaseStatisticsCte)
								   .bind(qDatabaseStatisticsCte.id.applicationId, qTempLatestDatabaseStatistics.id.applicationId)
								   .bind(qDatabaseStatisticsCte.id.timestamp, qTempLatestDatabaseStatistics.id.timestamp.max())
								   .from(qTempLatestDatabaseStatistics)
								   .groupBy(qTempLatestDatabaseStatistics.id.applicationId), qDatabaseStatisticsCte)
				.on(qDatabaseStatisticsCte.id.eq(qLatestDatabaseStatistics.id));
	}

	public static BlazeJPAQuery<DatabaseStatus> latestDatabaseStatusSubQuery(UUID applicationId) {
		QDatabaseStatus qLatestDatabaseStatus = new QDatabaseStatus("latestDatabaseStatus");
		QDatabaseStatus qTempLatestDatabaseStatus = new QDatabaseStatus("tempLatestDatabaseStatus");
		QDatabaseStatusCte qDatabaseStatusCte = QDatabaseStatusCte.databaseStatusCte;

		return JPQLNextExpressions
				.select(qLatestDatabaseStatus)
				.from(qLatestDatabaseStatus)
				.innerJoin(JPQLNextExpressions
								   .select(qDatabaseStatusCte)
								   .bind(qDatabaseStatusCte.id.applicationId, qTempLatestDatabaseStatus.id.applicationId)
								   .bind(qDatabaseStatusCte.id.timestamp, qTempLatestDatabaseStatus.id.timestamp.max())
								   .from(qTempLatestDatabaseStatus)
								   .where(qTempLatestDatabaseStatus.id.applicationId.eq(applicationId))
								   .groupBy(qTempLatestDatabaseStatus.id.applicationId), qDatabaseStatusCte)
				.on(qDatabaseStatusCte.id.eq(qLatestDatabaseStatus.id));
	}
}
