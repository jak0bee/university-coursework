function [error] = ABfunction(h)
% Initialize Variables
dydt = @(t, y) sin(2*t) + y - y^4;
h = h;
t0 = 0;
tf = 1;
t = t0:h:tf;
y0 = 0;
y = zeros(1,length(t));
exact = 0.9426297327;
% Runge-Kutta 3rd order Method
k0 = h * dydt(t0,y0);
k1 = h * dydt(t0+0.5*h,y0+0.5*k0);
k2 = h * dydt(t0+h,y0-k0+2*k1);
y(2) = y0 + 1/6*(k0+4*k1+k2);
% 2-stage Adams-Bashforth method
for index = 2 : (tf/h+1)
  y(index+1)=y(index) + (h/2)*(3*dydt((index)*h,y(index)) - dydt((index-1)*h, y(index-1)));
end
error = abs(y(tf/h+1)-exact);
end