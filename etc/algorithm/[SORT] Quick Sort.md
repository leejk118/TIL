# Quick Sort

배열에 있는 수 중 사용자가 지정한 규칙대로 임의의 pivot을 잡고, 해당 pivot을 기준으로 작거나 같은 수를 왼쪽 파티션, 큰 수를 오른쪽 파티션으로 보내고 다시 왼쪽 파티션 구간에 한하여 피벗을 잡고 파티션을 나누고 오른쪽 구간도 마찬가지로 반복하여 정렬시키는 정렬 알고리즘.

피벗을 잘 설정하여 적절하게 나누면 merge sort와 같은 O(nlogn)에 수행할 수 있지만, 매 케이스마다 구간의 가장 큰 값이나 가장 작은 값으로 나눠버리면 O(n^2)의 시간복잡도를 가지게 됨.



```java
void quickSort(int arr[], int left, int right){
    int pivot;
    int left_temp, right_temp;
    left_temp = left;
    right_temp = right;
    pivot = arr[left];
    while (left < right){
        while (arr[right] >= pivot && left < right)
            right--;
        if (left != right)
    }
}
```

