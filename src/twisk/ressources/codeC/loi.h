#ifndef LOI_H
#define LOI_H

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <unistd.h>

void delaiUniforme(int temps, int delta);
void delaiGauss(double moyenne, double ecartype);
void delaiExponentiel(double lambda);

#endif