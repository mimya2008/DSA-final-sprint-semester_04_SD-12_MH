package com.keyin.binarytree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BinaryNodeControllerTest {

    @Mock
    BinaryNodeService svc;

    @InjectMocks
    BinaryNodeController controller; // constructed with mocked svc

    @Test
    void buildTree_returnsTree() {
        // Arrange
        BinaryNode root = new BinaryNode(8);
        root.setLeft(new BinaryNode(4));
        root.setRight(new BinaryNode(12));
        when(svc.buildTreeFromList(anyList(), anyString())).thenReturn(root);

        // Act
        BinaryNode out = controller.buildBinarySearchTree(List.of(8, 4, 12));

        // Assert
        assertNotNull(out);
        assertEquals(8, out.getValue());
        assertEquals(4, out.getLeft().getValue());
        assertEquals(12, out.getRight().getValue());

        // verify numbers list was passed through
        ArgumentCaptor<List<Integer>> listCap = ArgumentCaptor.forClass(List.class);
        verify(svc).buildTreeFromList(listCap.capture(), anyString());
        assertEquals(List.of(8,4,12), listCap.getValue());
    }

    @Test
    void buildTreeFromString_parses_then_builds() {
        // Arrange
        String raw = "7, 3 9";
        List<Integer> parsed = List.of(7,3,9);
        when(svc.parseNumbers(raw)).thenReturn(parsed);

        BinaryNode root = new BinaryNode(7);
        root.setLeft(new BinaryNode(3));
        root.setRight(new BinaryNode(9));
        when(svc.buildTreeFromList(parsed, raw)).thenReturn(root);

        // Act
        BinaryNode out = controller.buildBinarySearchTreeFromString(raw);

        // Assert
        assertNotNull(out);
        assertEquals(7, out.getValue());
        assertEquals(3, out.getLeft().getValue());
        assertEquals(9, out.getRight().getValue());

        verify(svc).parseNumbers(raw);
        verify(svc).buildTreeFromList(parsed, raw);
        verifyNoMoreInteractions(svc);
    }

    @Test
    void getPrevious_returnsList() {
        TreeStructure t = new TreeStructure();
        t.setTreeJson("{\"value\":1}");
        t.setUserInputs("[1]");
        when(svc.getPrevious()).thenReturn(List.of(t));

        List<TreeStructure> out = controller.getPreviousTrees();

        assertEquals(1, out.size());
        assertEquals("{\"value\":1}", out.get(0).getTreeJson());
        assertEquals("[1]", out.get(0).getUserInputs());
        verify(svc).getPrevious();
    }
}
