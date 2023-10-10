monty <- function(switch=F) {
  doors = sample(c('car', 'goat', 'goat'), 3)
  pick = sample(1:3, 1)
  if (switch) {
    return(doors[pick] != 'car')
  } else {
    return(doors[pick] == 'car')
  }
}

no_switch = sapply(1:1000, FUN=function(i) monty())
switch = sapply(1:1000, FUN=function(i) monty(switch=T))
#Count the number of truths
values <- c(sum(switch), sum(no_switch))
names <- c("Switch", "No Switch")
#plot the comparision
plot <-barplot(values, names.arg=names, main="Barplot", ylim =c(0,1000), ylab="Number of wins after 1000 tries")
text(plot, values + 2, labels=values, pos=3)
