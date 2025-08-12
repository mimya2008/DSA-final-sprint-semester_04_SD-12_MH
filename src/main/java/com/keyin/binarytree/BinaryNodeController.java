package com.keyin.binarytree;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BinaryNodeController {
    private final BinaryNodeService svc;

    public BinaryNodeController(BinaryNodeService svc) { this.svc = svc; }

    @PostMapping("/buildTree")
    public BinaryNode buildBinarySearchTree(@RequestBody List<Integer> numbers) {
        String original = numbers.toString();
        return svc.buildTreeFromList(numbers, original);
    }


    @PostMapping("/buildTreeFromString")
    public BinaryNode buildBinarySearchTreeFromString(@RequestBody String numbersRaw) {
        var nums = svc.parseNumbers(numbersRaw);
        return svc.buildTreeFromList(nums, numbersRaw);
    }

    @GetMapping("/getPreviousTrees")
    public List<TreeStructure> getPreviousTrees() {
        return svc.getPrevious();
    }
}

