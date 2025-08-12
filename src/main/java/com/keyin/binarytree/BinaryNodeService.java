package com.keyin.binarytree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BinaryNodeService {
    private final BinaryNodeRepository nodeRepo;
    private final TreeStructureRepository treeRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public BinaryNodeService(BinaryNodeRepository nodeRepo, TreeStructureRepository treeRepo) {
        this.nodeRepo = nodeRepo; this.treeRepo = treeRepo;
    }

    public BinaryNode buildTreeFromList(List<Integer> numbers, String originalInput) {
        BinaryNode root = null;
        for (int n : numbers) root = insert(root, n);
        nodeRepo.save(root);
        saveSnapshot(root, originalInput, numbers);
        return root;
    }

    public List<TreeStructure> getPrevious() {
        return treeRepo.findAll();
    }

    private BinaryNode insert(BinaryNode root, int v) {
        if (root == null) return new BinaryNode(v);
        if (v <= root.getValue()) root.setLeft(insert(root.getLeft(), v));
        else root.setRight(insert(root.getRight(), v));
        return root;
    }

    private void saveSnapshot(BinaryNode root, String originalInput, List<Integer> numbers) {
        try {
            String json = mapper.writeValueAsString(root); // compact JSON
            String canon = numbers.stream().map(String::valueOf).collect(Collectors.joining(","));
            if (treeRepo.findByCanonicalInputs(canon).isEmpty()) {
                treeRepo.save(new TreeStructure(json, originalInput, canon));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> parseNumbers(String raw) {
        if (raw == null || raw.isBlank()) return List.of();
        String[] parts = raw.trim().split("[,\\s]+");
        List<Integer> out = new ArrayList<>(parts.length);
        for (String p : parts) out.add(Integer.parseInt(p));
        return out;
    }
}
