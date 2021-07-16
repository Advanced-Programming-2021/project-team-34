...|
CommandHelper for java version 2 by Mahdi Teymoori Anar
name : CommandHelper
language : java
version : 2
author : Mahdi Teymoori Anar

What's new in this version :
{
    * supporting arguments for fields between two quotation with SPACE
    (in version 1 arguments couldn't have SPACE character)
    * in CommandType instances you can addField(name , /* new */ boolean doesHaveArgument)
    instead of addField(name) then .getField(name).setDoesHaveArgument(boolean value)
    how ever you can do previous method.
}