function [error] = AMfunction(h)
% Initialize Variables
dydt = @(t, y) sin(2*t) + y - y^4;
h = h;
t0 = 0;
tf = 1;
t = t0:h:tf;
y0 = 0;
y = zeros(1,length(t));
exact = 0.9426297327;
% First Step - Runge-Kutta 3rd order Method for both w1 and w2
%w1
k10 = h * dydt(t0,y0);
k11 = h * dydt(t0+0.5*h,y0+0.5*k10);
k12 = h * dydt(t0+h,y0-k10+2*k11);
y(2) = y0 + 1/6*(k10+4*k11+k12);
%w2
k20 = h * dydt(h,y(2));
k21 = h * dydt(h +0.5 * h,y(2)+0.5*k20);
k22 = h * dydt(h +h,y(2)-k20+2*k21);
y(3) = y(2) + 1/6*(k20+4*k21+k22);
% 2-stage Adams-Moulton method
for index = 3 : (tf/h+1)
    %Using the secant method
    f = @(Y_n_plus_1) Y_n_plus_1 - y(index) - (h/12)*( 5*dydt((index+1)*h,Y_n_plus_1) + 8*dydt(index*h,y(index)) - dydt((index-1)*h,y(index-1)));
    % Initial guesses for the secant method
    ynp1_0 = y(index) + 0.1;
    ynp1_1 = ynp1_0 + 0.1; 
    % Perform secant method iterations
    for i = 1:8 % Maximum of 8 iterations
        ynp1 = ynp1_1 - f(ynp1_1)*(ynp1_1 - ynp1_0)/(f(ynp1_1) - f(ynp1_0));
        if abs(ynp1 - ynp1_1) < eps
            break;
        end
        ynp1_0 = ynp1_1;
        ynp1_1 = ynp1;
    end
    nextY = ynp1;
  y(index+1) = y(index) + (h/12)*( 5*dydt((index+1)*h,nextY) + 8*dydt(index*h,y(index)) - dydt((index-1)*h,y(index-1)));
end
error = (abs(y(tf/h+1)-exact));

end