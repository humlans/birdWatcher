package org.example.algorithms;

import org.example.items.BirdSpecies;
import java.util.ArrayList;

public class QuickSort {

    public void sortBirdSpeciesAlphabetically(ArrayList<BirdSpecies> birdSpeciesList, int min, int max){
        if(min < max){
            int pivotIndex = partition(birdSpeciesList, min, max);
            sortBirdSpeciesAlphabetically(birdSpeciesList, min, pivotIndex - 1);
            sortBirdSpeciesAlphabetically(birdSpeciesList, pivotIndex + 1, max);
        }
    }
    private int partition(ArrayList<BirdSpecies> birdSpeciesList, int min, int max){
        int pivotIndex = max;
        BirdSpecies pivotValue = birdSpeciesList.get(pivotIndex);

        int middleIndex = min;
        swap(birdSpeciesList, pivotIndex, max);
        for(int i = min; i < max; i++){
            if(birdSpeciesList.get(i).getEnglishName().compareToIgnoreCase(pivotValue.getEnglishName()) <= 0){
                swap(birdSpeciesList, middleIndex, i);
                middleIndex++;
            }
        }
        swap(birdSpeciesList, middleIndex, max);
        return middleIndex;
    }

    private void swap(ArrayList<BirdSpecies> birdSpeciesList, int index1, int index2){
        BirdSpecies temp = birdSpeciesList.get(index1);
        birdSpeciesList.set(index1, birdSpeciesList.get(index2));
        birdSpeciesList.set(index2, temp);
    }
}
