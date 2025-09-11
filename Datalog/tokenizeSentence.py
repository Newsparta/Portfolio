import nltk
from nltk import pos_tag, word_tokenize

# Tokenize and tag the sentence
paragraph = "paragraph1"  # Specify paragraph
sentence = "sentence1"  # Specify sentence
text = "The quick brown fox jumps over the lazy dog"
tokens = word_tokenize(text)
tagged = pos_tag(tokens)

# Export to file in the desired format
with open("token.facts", "w") as file:
    for index, (word, tag) in enumerate(tagged, start=1):
        file.write(f"{paragraph}\t{sentence}\t{word}\t{index}\t{tag}\n")