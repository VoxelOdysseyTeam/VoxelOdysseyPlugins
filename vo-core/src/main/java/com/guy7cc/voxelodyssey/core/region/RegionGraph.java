package com.guy7cc.voxelodyssey.core.region;

import java.util.*;

public class RegionGraph {
    private RegionShape root;
    private Map<RegionShape, Vertex> graph = new HashMap<>();

    public RegionGraph(){
        root = new UniversalRegionShape();
        graph.put(root, new Vertex());
    }

    public boolean add(RegionShape shape){
        if(graph.containsKey(shape)) return false;

        // collect parents
        Queue<RegionShape> maybeParents = new ArrayDeque<>();
        maybeParents.add(root);
        Set<RegionShape> trueParents = new HashSet<>();
        while(!maybeParents.isEmpty()){
            RegionShape p = maybeParents.poll();
            boolean isDirectParent = true;
            for(RegionShape childOfParent : getChildren(p)){
                if(childOfParent.contains(shape)){
                    isDirectParent = false;
                    maybeParents.add(childOfParent);
                }
            }
            if(isDirectParent) trueParents.add(p);
        }

        // collect children
        Set<RegionShape> maybeChildren = graph.keySet();
        Set<RegionShape> trueChildren = new HashSet<>();
        while(!maybeChildren.isEmpty()){
            RegionShape c = maybeChildren.iterator().next();
            maybeChildren.remove(c);
            if(shape.contains(c)){
                boolean isDirectChild = true;
                for(var parentOfChild : getParents(c)){
                    if(shape.contains(parentOfChild)){
                        isDirectChild = false;
                    } else {
                        maybeChildren.remove(parentOfChild);
                    }
                }
                if(isDirectChild) trueChildren.add(c);
            }
        }

        // register vertex
        Vertex v = new Vertex(trueParents, trueChildren, List.of());
        graph.put(shape, v);

        // remove my children from children set of my parents
        for(RegionShape ps : v.parents){
            Vertex pv = graph.get(ps);
            pv.children.removeAll(v.children);
        }

        // remove my parents from parents set of my children
        for(RegionShape cs : v.children){
            Vertex cv = graph.get(cs);
            cv.parents.removeAll(v.parents);
        }

        return true;
    }

    public void addHandler(RegionShape shape, RegionHandler handler){
        if(!graph.containsKey(shape)) add(shape);
        graph.get(shape).handlers.add(handler);
    }

    public int size(){
        return graph.size();
    }

    public Set<RegionShape> getParents(RegionShape shape){
        if(!graph.containsKey(shape)) add(shape);
        return Collections.unmodifiableSet(graph.get(shape).parents);
    }

    public Set<RegionShape> getChildren(RegionShape shape){
        if(!graph.containsKey(shape)) add(shape);
        return Collections.unmodifiableSet(graph.get(shape).children);
    }

    private record Vertex(Set<RegionShape> parents, Set<RegionShape> children, List<RegionHandler> handlers){
        public Vertex(){
            this(Set.of(), Set.of(), List.of());
        }
    }
}
