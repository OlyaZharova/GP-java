package com.gp_solutions.task1;

import java.util.Scanner;

public class Task1 {


    public static void main(String[] args) {

        Task1 test_project1 = new Task1();
        int[][] graph = test_project1.initGraph();
        int[][] newGraph = test_project1.initNewGraph(graph);
        newGraph = test_project1.algorithm(graph, newGraph);
        test_project1.result(newGraph);

    }

    private int[][] algorithm(int[][] graph, int[][] newGraph) {
        for (int y = 1; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                int diagonalUp = x - 1;
                int diagonalDown = x + 1;
                algorithmStep(graph, newGraph, x, y, diagonalUp);
                algorithmStep(graph, newGraph, x, y, diagonalDown);
                algorithmStep(graph, newGraph, x, y, x);
            }
        }
        return newGraph;
    }

    private void algorithmStep(int[][] graph, int[][] newGraph, int x, int y, int newX) {
        if (newX >= 0 && newX < graph.length) {
            if (graph[newX][y] + newGraph[x][y - 1] > newGraph[newX][y]) {
                newGraph[newX][y] = graph[newX][y] + newGraph[x][y - 1];
            }
        }
    }

    private int[][] initGraph() {
        int size = initSize();
        int[][] graph = new int[size][size];
        for (int x = 0; x < graph.length; x++) {
            for (int y = 0; y < graph.length; y++) {
                graph[x][y] = 0 + (int) (Math.random() * 10);
            }
        }
        for (int x = 0; x < graph.length; x++) {
            for (int y = 0; y < graph[x].length; y++) {
                System.out.print(graph[x][y] + " ");
            }
            System.out.println();
        }
        return graph;
    }

    private int initSize() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        boolean flag = true;
        while (flag) {
            System.out.print("Enter the size of the array: ");
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                if (i > 0) {
                    flag = false;
                } else {
                    System.out.println("You entered a negative number");
                }
            } else {
                sc.next();
                System.out.println("You didn't enter an integer");
            }
        }
        return i;
    }

    private int[][] initNewGraph(int[][] graph) {
        int[][] newGraph = new int[graph.length][graph.length];
        for (int x = 0; x < newGraph.length; x++) {
            for (int y = 0; y < newGraph[x].length; y++) {
                if (y == 0) {
                    newGraph[x][y] = graph[x][y];
                } else {
                    newGraph[x][y] = 0;
                }
            }
        }
        return newGraph;
    }

    private int findLongestPath(int[][] newGraph) {
        int max = 0;
        for (int x = 0; x < newGraph.length; x++) {
            for (int y = 0; y < newGraph[x].length; y++) {
                if (newGraph[x][y] > 0 && newGraph[x][y] > max) {
                    if (newGraph[x][y] > max) {
                        max = newGraph[x][y];
                    }
                }
            }
        }
        return max;
    }

    private void result(int[][] newGraph) {
        System.out.println("all longest paths tree from initialVertex:");
        for (int x = 0; x < newGraph.length; x++) {
            for (int y = 0; y < newGraph[x].length; y++) {
                System.out.printf("initialVertex -> [%d][%d] = %d \n", x, y, newGraph[x][y]);
            }
        }
        System.out.printf("longest path tree from vertex 'initialVertex' = %d", findLongestPath(newGraph));
    }

}

