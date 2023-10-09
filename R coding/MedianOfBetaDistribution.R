# Define the parameters
a = 7
b = 2

# A simple beta approximation from a paper (Reference in the document)
approximation = (a-1/3)/(a+b-2/3)

# Define the function for the CDF of the Beta distribution
cdf_beta <- function(x, a, b) {
  integrate(function(t) dbeta(t, a, b), 0, x)$value
}

# Find the value of x for which CDF(x) = 0.5
func <- function(x) {
  cdf_beta(x, a, b) - 0.5
}

# Find the root, which is the median
result = uniroot(func, c(0, 1))$root

# Plot the Beta distribution
p = seq(0, 1, length=1000)
plot(p, dbeta(p, a, b), type='l')
abline(v=result, col="blue")
abline(v=approximation, col="red")
legend("topright", legend=c(paste("Median =", round(result, 4)), paste("Approximation =", round(approximation, 4))),col=c("blue", "red"))
