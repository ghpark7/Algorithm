#include <iostream>
#include <string>

using namespace std;

string N;
int sum1, sum2 = 0;

int main(){
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 0; i < N.size() / 2; i++){
        sum1 += N[i];
    }
    for(int j = N.size() / 2; j < N.size(); j++){
        sum2 += N[j];
    }
    if(sum1 == sum2) cout << "LUCKY\n";
    else cout << "READY\n";
    return 0;
}