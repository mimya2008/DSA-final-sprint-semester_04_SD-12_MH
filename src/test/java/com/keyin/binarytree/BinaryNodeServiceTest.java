package com.keyin.binarytree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
// import java.util.Optional;  // uncomment if you verify/stub dedupe

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BinaryNodeServiceTest {

    @Mock BinaryNodeRepository nodeRepo;
    @Mock TreeStructureRepository treeRepo;

    @InjectMocks BinaryNodeService svc;

    @Test
    void parseNumbers_handles_commas_and_spaces() {
        List<Integer> nums = svc.parseNumbers("8, 4  12,2 6 10,14");
        assertEquals(List.of(8,4,12,2,6,10,14), nums);
    }

    @Test
    void buildTreeFromList_builds_expected_shape_and_saves() {
        // let saves echo back the entity so we don't get NPEs
        when(nodeRepo.save(any(BinaryNode.class))).thenAnswer(inv -> inv.getArgument(0));
        when(treeRepo.save(any(TreeStructure.class))).thenAnswer(inv -> inv.getArgument(0));

        // If you added canonical de-duplication in your service, you can stub it like this:
        // when(treeRepo.findByCanonicalInputs(anyString())).thenReturn(Optional.empty());

        var root = svc.buildTreeFromList(List.of(8,4,12,2,6,10,14), "8,4,12,2,6,10,14");

        assertNotNull(root);
        assertEquals(8,  root.getValue());
        assertEquals(4,  root.getLeft().getValue());
        assertEquals(12, root.getRight().getValue());
        assertEquals(2,  root.getLeft().getLeft().getValue());
        assertEquals(6,  root.getLeft().getRight().getValue());
        assertEquals(10, root.getRight().getLeft().getValue());
        assertEquals(14, root.getRight().getRight().getValue());

        // verify expected calls
        verify(nodeRepo, times(1)).save(any(BinaryNode.class));
        verify(treeRepo, atLeastOnce()).save(any(TreeStructure.class));

        // IMPORTANT: don't forbid extra interactions on treeRepo (e.g., findByCanonicalInputs)
        verifyNoMoreInteractions(nodeRepo);
        // (intentionally NOT calling verifyNoMoreInteractions(treeRepo))
    }
}
