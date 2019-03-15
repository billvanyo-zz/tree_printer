# tree_printer
A Java class for printing binary trees as ASCII text

It hasn't been optimized for run time efficiency, but since we're talking about printing in ASCII, I figured it's not going to be used on very large trees.  It does have some nice features though.

 1. It makes efficient use of space in that a large subtree extends under a smaller one as much as possible.
 2. There's a parameter to set the minimum horizontal space between node labels.
 3. Node labels are strings of arbitrary length.
 4. In addition to a method for printing a single tree, there's a method for printing a list of trees horizontally across the page (with a parameter for page width), using as many rows as necessary.
 5. There's an option to print trees with diagonal branches (using slash and backslash characters) or with horizontal branches (using ascii box drawing characters). The latter is more compact and makes tree levels more visually clear.
 6. It works.

Some demo/test programs are included.

The Java class TreePrinter has four static methods for printing binary trees as ASCII text.

PrintTree prints a single tree.  In addition to the tree, it takes a positive integer parameter 'hspace' which is the minimum number of horizontal spaces between any two node labels in the tree.

A variant of PrintTree takes an additional boolean parameter, which if true, results in trees being printed with horizontal branches (using ascii box drawing characters).

PrintTrees prints a list of trees horizontally across the page, in multiple rows if necessary.  In addition to the tree and 'hspace' parameter, it takes a 'tspace' parameter which is the number of space columns between trees horizontally, as well as the number of blank lines between rows of trees, and a 'lineWidth' parameter which is the maximum width of the page.

A variant of PrintTrees also takes an additional boolean parameter, which if true, results in trees being printed with horizontal branches (using ascii box drawing characters).

A few test/demo programs are included.  For instance, the program EnumTrees can be used to print an enumeration of all binary trees of a given size.  All trees of size 5, labeled with number words (one, two, etc) is printed as:

```
one           one         one          one        one       one         one       one          one      
  \             \           \            \          \         \           \         \            \      
  two           two         two          two        two      three       three      four         four   
    \             \           \            \          \       / \         / \       / \          / \    
   three         three        four         five       five  two four    two five  two five      /   \   
      \             \         / \          /          /           \         /       \        three  five
      four          five     /   \      three       four          five    four     three      /         
        \           /     three  five      \        /                                       two         
        five      four                     four  three                                                  



one        one       one       one         one        two             two          two          two   
  \          \         \         \           \        / \             / \          / \          / \   
  five       five      five      five        five    /   \           /   \       one four     one five
  /          /         /         /           /     one  three      one  three        / \          /   
two        two      three      four        four            \               \        /   \      three  
  \          \       / \       /           /               four            five  three  five      \   
 three       four  two four  two        three                \             /                      four
    \        /                 \         /                   five        four                         
    four  three               three    two                                                            



   two      three         three         three         three      four      four         four  
   / \       / \           / \           / \           / \       / \       / \          / \   
 one five  one four       /   \        two four      two five  one five  one five     two five
     /       \   \       /     \       /     \       /   /       \         \          / \     
   four      two five  one     five  one     five  one four      two      three      /   \    
   /                     \     /                                   \       /       one  three 
three                    two four                                 three  two                  



     four            four       five        five      five      five      five       five         five
     / \             / \        /           /         /         /         /          /            /   
    /   \           /   \     one         one       one       one       one        two          two   
 three  five     three  five    \           \         \         \         \        / \          / \   
  /               /             two         two      three      four      four    /   \       one four
one             two               \           \       / \       /         /     one  three        /   
  \             /                three        four  two four  two      three            \      three  
  two         one                   \         /                 \       /               four          
                                    four   three               three  two                             



    five        five      five      five         five        five          five
    /           /         /         /            /           /             /   
 three       three      four      four         four        four          four  
  / \         / \       /         /            /           /             /     
one four    two four  one       one          two        three         three    
  \         /           \         \          / \         /             /       
  two     one           two      three      /   \      one           two       
                          \       /       one  three     \           /         
                         three  two                      two       one         
```
RandomTree can be used to print a single randomly generated tree.  The following is an example of the same tree printed 4 different ways, with horizontal spacing of 1 and of 3, and with diagonal and horizontal branches.

                   27        
             ┌─────┴─────┐   
             13          29  
      ┌──────┴──────┐  ┌─┴─┐ 
      8             23 28  30
   ┌──┴──┐       ┌──┴──┐     
   4     11      21    26    
 ┌─┴─┐  ┌┴┐    ┌─┴─┐  ┌┘     
 2   5  9 12   18  22 24     
┌┴┐  └┐ └┐   ┌─┴─┐    └┐     
1 3   6  10  17  19    25    
      └┐    ┌┘   └┐          
       7    15    20         
          ┌─┴─┐              
          14  16             


                 27        
                / \        
               /   \       
              13    29     
             / \   / \     
            /   \ 28  30   
           /     \         
          /       \        
         /         \       
        /           \      
       8             23    
      / \           / \    
     /   \         /   \   
    4     11      /     \  
   / \   / \     21      26
  2   5 9   12  / \     /  
 / \   \ \     18  22  24  
1   3   6 10  / \       \  
         \   17  19      25
          7 /     \        
           15      20      
          / \              
         14  16            


                             27            
                    ┌────────┴────────┐    
                    13                29   
          ┌─────────┴─────────┐    ┌──┴──┐ 
          8                   23   28    30
     ┌────┴────┐         ┌────┴────┐       
     4         11        21        26      
  ┌──┴──┐    ┌─┴─┐    ┌──┴──┐     ┌┘       
  2     5    9   12   18    22    24       
┌─┴─┐   └┐   └┐    ┌──┴──┐        └┐       
1   3    6    10   17    19        25      
         └┐       ┌┘     └┐                
          7       15      20               
               ┌──┴──┐                     
               14    16                    


                      27         
                     / \         
                    /   \        
                   /     \       
                  /       \      
                 13        29    
                / \       / \    
               /   \     /   \   
              /     \   28    30 
             /       \           
            /         \          
           /           \         
          /             \        
         /               \       
        8                 23     
       / \               / \     
      /   \             /   \    
     /     \           /     \   
    4       11        /       \  
   / \     / \       21        26
  2   5   9   12    / \       /  
 / \   \   \       /   \     24  
1   3   6   10    18    22    \  
         \       / \           25
          7     /   \            
               17    19          
              /       \          
             15        20        
            / \                  
           /   \                 
          14    16               
```

```

