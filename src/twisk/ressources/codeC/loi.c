#include "loi.h"

void delaiUniforme(int temps, int delta) {
    int bi, bs ;
    int n, nbSec ;
    bi = temps - delta ;
    if (bi < 0) bi = 0 ;
    bs = temps + delta ;
    n = bs - bi ;
    nbSec = (rand()/ (float)RAND_MAX) * n ;
    nbSec += bi ;
    usleep(nbSec * 1000000);
}

void delaiGauss(double moyenne, double ecartype) {
    double U1 = rand() / (RAND_MAX + 1.0);
    double U2 = rand() / (RAND_MAX + 1.0);
    double x = sqrt(-(2*log(U1))) * cos(2* M_PI * U2) * ecartype + moyenne;
    usleep(x * 1000000.0);
}

void delaiExponentiel(double lambda) {
    double U = rand() / (RAND_MAX + 1.0);
    double x = -log(U)/lambda;
    usleep(x * 1000000.0);
}

int main() {
    srand(time(NULL));
    for (int i = 0; i < 5000; i++) {
        delaiExponentiel(1.0/10.0);
    }
}