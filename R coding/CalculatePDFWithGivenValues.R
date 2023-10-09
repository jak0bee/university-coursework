f <- function(x) {
  if (x >= 3 && x < 5) {
    return((x - 3) / 5)
  } else if (x >= 5 && x <= 7) {
    return((7 - x) / 5)
  } else {
    return(0)
  }
}

x_values <- seq(0, 10, length.out=1000)  # 1000 points between 0 and 10

# Calculate the PDF for each x value
pdf_values <- sapply(x_values, f)

# Plot the PDF
plot(x_values, pdf_values, type='l', xlab='X', ylab='PDF',
     main='PDF')