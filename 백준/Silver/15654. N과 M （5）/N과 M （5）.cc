#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m, k;
vector<int> v;
bool visited[10] = {false};
int arr[10];

void func(int x){
    if(x == m){
        for(int i = 0; i < m; i++) cout << arr[i] << " ";
        cout << "\n";
        return;
    }
    for(int i = 0; i < n; i++){
        if(visited[i]) continue;
        arr[x] = v[i];
        visited[i] = true;
        func(x+1);
        visited[i] = false;
    }
    return;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> m;
    for(int i = 0; i < n; i++){
        cin >> k;
        v.push_back(k);
    }
    sort(v.begin(), v.end());
    func(0);
    return 0;
}