# Reflection on implementing regular expressions of a DSL

## Which operators were easiest to implement and why?
Union, Concat, Star, and Plus were all pretty easy to implement, as they used a
syntax that I was familiar with from PLs. The examples that we did in class
with postfix operators definitely helped as well, like minutes in the Stopwatch
class.

## Which operators were most difficult to implement and why?
The {n} operator was very difficult for me to implement; while I intuitively
felt like I knew how to implement it, I was really struggling with making a
recursive solution for it. I ended up having to compromise a bit and
implement it using reduce, but I still felt like my solution was pretty
scala-y. I also was struggling to get good error messages that told me what
was wrong, so I definitely would like to find out more about debugging in
Scala.

## Comment on the design of this internal DSL

Write a few brief paragraphs that discuss:

- What works about this design? (For example, what things seem easy and
  natural to say, using the DSL?)
- What doesn't work about this design? (For example, what things seem
  cumbersome to say?)
- Think of a syntactic change that might make the language better. How would
  you implement it _or_ what features of Scala would prevent you from
  implementing it? (You don't have to write code for this part. You could say
  "I would use extension to..." or "Scala's rules for valid
  identifiers prevent...")

  I think the way the regular expressions is formed feel fairly natural, but it
definitely lacks some of the compactness of a true regular expression. I think
the star, plus, union, and {n} operators all retain most of the minimalism of
their counterparts, but being forced to sometimes use ~ for concatenation, an 
operator that usually requires no characters whatsoever, feels a bit clumsy. 
Being able to use a string to represent a sequence of character concatenations
definitely helps with this, although it doesn't completely solve the problem.
  It would be nice to be able to implement an order of operations, so that the
user wouldn't have to use quite so many parentheses. As far as I can tell, from
this StackOverflow thread at least, truly custom operator precedence is not
currently possible in Scala. If it was, like it is in Haskell, it would be
relatively trivial to add a coherent order of operations to this language.
https://stackoverflow.com/questions/2922347/operator-precedence-in-scala
