#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>

static int __init mychardriver_init(void)
{
    printk(KERN_INFO "Hello from MyCharDriver! Loaded successfully.\n");
    return 0;
}

static void __exit mychardriver_exit(void)
{
    printk(KERN_INFO "Goodbye from MyCharDriver! Unloaded.\n");
}

module_init(mychardriver_init);
module_exit(mychardriver_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mika");
MODULE_DESCRIPTION("Simple example character driver");
