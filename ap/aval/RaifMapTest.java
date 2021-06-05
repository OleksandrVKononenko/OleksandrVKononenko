package ap.aval;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RaifMapTest {

	/*
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAvaHashMap() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testToStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStringArray() {
		fail("Not yet implemented");
	}

	@Test
	void testMain() {
		fail("Not yet implemented");
	}

	@Test
	void testClone() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	void testContainsValue() {
		fail("Not yet implemented");
	}

	@Test
	void testContainsKey() {
		fail("Not yet implemented");
	}

	@Test
	void testGetObject() {
		fail("Not yet implemented");
	}

	@Test
	void testPut() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveObject() {
		fail("Not yet implemented");
	}

	@Test
	void testPutAll() {
		fail("Not yet implemented");
	}

	@Test
	void testClear() {
		fail("Not yet implemented");
	}

	@Test
	void testKeySet() {
		fail("Not yet implemented");
	}

	@Test
	void testValues() {
		fail("Not yet implemented");
	}

	@Test
	void testEntrySet() {
		fail("Not yet implemented");
	}

	@Test
	void testHash() {
		fail("Not yet implemented");
	}

	@Test
	void testComparableClassFor() {
		fail("Not yet implemented");
	}

	@Test
	void testCompareComparables() {
		fail("Not yet implemented");
	}

	@Test
	void testTableSizeFor() {
		fail("Not yet implemented");
	}

	@Test
	void testHashMapIntFloat() {
		fail("Not yet implemented");
	}

	@Test
	void testHashMapInt() {
		fail("Not yet implemented");
	}

	@Test
	void testHashMap() {
		fail("Not yet implemented");
	}

	@Test
	void testHashMapMapOfQextendsKQextendsV() {
		fail("Not yet implemented");
	}

	@Test
	void testPutMapEntries() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNode() {
		fail("Not yet implemented");
	}

	@Test
	void testPutVal() {
		fail("Not yet implemented");
	}

	@Test
	void testResize() {
		fail("Not yet implemented");
	}

	@Test
	void testTreeifyBin() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveNode() {
		fail("Not yet implemented");
	}

	@Test
	void testPrepareArray() {
		fail("Not yet implemented");
	}

	@Test
	void testKeysToArray() {
		fail("Not yet implemented");
	}

	@Test
	void testValuesToArray() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOrDefault() {
		fail("Not yet implemented");
	}

	@Test
	void testPutIfAbsent() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveObjectObject() {
		fail("Not yet implemented");
	}

	@Test
	void testReplaceKVV() {
		fail("Not yet implemented");
	}

	@Test
	void testReplaceKV() {
		fail("Not yet implemented");
	}

	@Test
	void testComputeIfAbsent() {
		fail("Not yet implemented");
	}

	@Test
	void testComputeIfPresent() {
		fail("Not yet implemented");
	}

	@Test
	void testCompute() {
		fail("Not yet implemented");
	}

	@Test
	void testMerge() {
		fail("Not yet implemented");
	}

	@Test
	void testForEach() {
		fail("Not yet implemented");
	}

	@Test
	void testReplaceAll() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadFactor() {
		fail("Not yet implemented");
	}

	@Test
	void testCapacity() {
		fail("Not yet implemented");
	}

	@Test
	void testNewNode() {
		fail("Not yet implemented");
	}

	@Test
	void testReplacementNode() {
		fail("Not yet implemented");
	}

	@Test
	void testNewTreeNode() {
		fail("Not yet implemented");
	}

	@Test
	void testReplacementTreeNode() {
		fail("Not yet implemented");
	}

	@Test
	void testReinitialize() {
		fail("Not yet implemented");
	}

	@Test
	void testAfterNodeAccess() {
		fail("Not yet implemented");
	}

	@Test
	void testAfterNodeInsertion() {
		//fail("Not yet implemented");
		
		AvaHashMap 	ahm = AvaHashMap.getInstance();
		
		
					ahm.put(0,"Default");
		
					assertEquals("DEFAULT",ahm.get(0));
	}

	@Test
	void testAfterNodeRemoval() {
		fail("Not yet implemented");
	}

	@Test
	void testInternalWriteEntries() {
		fail("Not yet implemented");
	}
	*/
	
	@Test
	void testAfterNodeInsertion() {
		
		RaifMap ahm = RaifMap.getInstance();

		ahm.put(0, "Default");

		assertEquals("Default", ahm.get(0));
	}

}
